$("#btn-upload .spinner").hide()
const tbody = $("#table-info-tbody")

function resetAll() {
    $("#field-file").get(0).value = ""
    $("#table-info-tbody tr:not(#info-tr-proto)").remove()
}

$("#field-file").on("change", e => {
    if ($("#field-file").get(0).length >= 1) {
        resetAll()
    } else {
        $("#table-info-tbody tr:not(#info-tr-proto)").remove()
    }
    const evTarget = e.target
    console.log(evTarget.files)
    Array.from(evTarget.files).forEach(file => {
        if (file.type != null) {
            const clone = $("#info-tr-proto").clone()
            clone.find(".file-name").text(file.name)
            clone.find(".category input").val("")
            const fileNameWithoutExt = file.name.substring(0, file.name.lastIndexOf("."))
            clone.find(".custom-title input").val(fileNameWithoutExt)
            clone.find(".genre input").val("")
            clone.find(".file-size").text(Math.ceil(file.size / 1000) + " KB")
            clone.show()
            clone.attr("id", "")
            tbody.append(clone)
        } else {
            console.log(file.name, "미디 파일만 업로드할 수 있습니다.")
        }
    })
})

$("#btn-upload").on("click", e => {
    const files = $("#field-file").get(0).files
    if (files.length == 0) {
        alert("업로드할 파일을 선택하세요.")
        return false
    }

    const singer = $("#table-info-tbody tr:not(#info-tr-proto) .category input").map((i, el) => $(el).val())
    const titles = $("#table-info-tbody tr:not(#info-tr-proto) .custom-title input").map((i, el) => $(el).val())
    const genre = $("#table-info-tbody tr:not(#info-tr-proto) .genre input").map((i, el) => $(el).val())
    const formData = new FormData()
    Array.from(files).forEach((file, i) => {
        formData.append("files", file)
        formData.append("singer", singer[i])
        formData.append("titles", titles[i])
        formData.append("genre", genre[i])
    })

    const entries = formData.entries()
    $("#btn-upload .spinner").show()
    $("#btn-upload .message").text("작업을 처리하고 있습니다.....")
    fetch("/api/v1/midi/", {
        method: "POST",
        body: formData
    })
        .then(res => res.json())
        .then(data => {
            console.log(data)
            if (data.status == "NotAllowManyFile") {
                alert("일반 회원은 최대 5개의 파일만 업로드할 수 우 있습니다.")
                return
            }

            $(".modal").find(".upload-result").text(data.status)
            $(".modal").find(".success-count").text(data.successList.length)
            $(".modal").find(".failed-count").text(data.failedList.length)
            $(".modal").modal("show")

            resetAll()
        })

        .catch(err => {
            console.error(err)

            $(".modal").find(".upload-result").text("파일 전송에 실패했습니다.")
            $(".modal").modal("show")

            resetAll()
        })

        .finally(() => {
            $("#btn-upload .spinner").hide()
            $("#btn-upload .message").text("모든 파일 업로드")
        })
})

$("#btn-reset").on("click", e => {
    resetAll()
})