package com.example.mp3player.utill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.UploadResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlayUtill {

	public PlayUtill play(Long id, HttpServletRequest request, HttpServletResponse response,
		UploadService uploadService) {
		// mp3 파일 경로 지정
		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		String basePath = rootPath + "/" + "app/midi";
		UploadResponseDTO mp3 = uploadService.findById(id);
		String mp3Path = basePath + mp3.getOriginalMp3Path();

		// File 객체 생성
		File initFile = new File(mp3Path);

		// ***** RANGE 추출 ***** //
		Long startRange = 0l;
		Long endRange = 0l;
		Boolean isPartialRequest = false;

		try {
			if (request.getHeader("range") != null) {
				String rangeStr = request.getHeader("range");

				System.out.println(rangeStr);

				String[] range = rangeStr
					.replace("bytes=", "").split("-");

				startRange = range[0] != null ? Long.parseLong(range[0]) : 0l;
				if (range[1] != null) {
					endRange = Long.parseLong(range[1]);
					isPartialRequest = true;
				}

				System.out.println(">>>>> range >>>>> " + range[0] + " : " + range[1]);
			}
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			System.err.println(e);
		}
		// ****************** //

		// 리스폰스 헤더 설정
		response.setHeader("Content-Disposition", "filename=\"" + "\"");
		response.setContentType("audio/mpeg");
		response.setHeader("Accept-Ranges", "bytes"); // 크롬 구간문제 해결용
		response.setHeader("Content-Transfer-Encoding", "binary;");

		// 부분 범위 리퀘스트인지, 전체 범위 리퀘스트인지에 따라 Content-Range 값을 다르게
		if (isPartialRequest) {
			response.setHeader("Content-Range", "bytes " + startRange + "-"
				+ endRange + "/" + initFile.length());
		} else {
			response.setHeader("Content-Length", initFile.length() + "");
			response.setHeader("Content-Range", "bytes 0-"
				+ initFile.length() + "/" + initFile.length());
			startRange = 0l;
			endRange = initFile.length();
		}

		// 랜덤 액세스 파일을 이용해 mp3 파일을 범위로 읽기
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(initFile, "r");
			 ServletOutputStream sos = response.getOutputStream();) {

			Integer bufferSize = 1024, data = 0;
			byte[] b = new byte[bufferSize];
			Long count = startRange;
			Long requestSize = endRange - startRange + 1;

			// startRange에서 출발
			randomAccessFile.seek(startRange);

			while (true) {
				// 버퍼 사이즈 (1024) 보다 범위가 작으면
				if (requestSize <= 2) {
					// Range byte 0-1은 아래 의미가 아님.
					// data = randomAccessFile.read(b, 0, requestSize.intValue());
					// sos.write(b, 0, data);

					// ** write 없이 바로 flush ** //
					sos.flush();
					break;
				}

				// 나머지는 일반적으로 진행
				data = randomAccessFile.read(b, 0, b.length);

				// count가 endRange 이상이면 요청 범위를 넘어선 것이므로 종료
				if (count <= endRange) {
					sos.write(b, 0, data);
					count += bufferSize;
					randomAccessFile.seek(count);
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
