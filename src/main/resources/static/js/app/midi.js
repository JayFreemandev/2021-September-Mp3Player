const midi = {
    playerInit() {
        Number.prototype.toMS = function(isMillisecond) {
            if (isMillisecond) {
                var num = parseInt(this) / 1000
            } else {
                var num = parseInt(this)
            }

            // console.log(num)
            var min = Math.floor(num / 60)
            var sec = Math.floor(num - (min * 60))
            if (min < 10) {
                min = "0" + min;
            }
            if (sec < 10) {
                sec = "0" + sec;
            }
            return min + ":" + sec
        }


        // 현재 재생중인 곡 정보를 담는 객체
        const currentPlay = {
            trEl: null,
            fullEl: [],
            isShuffleOn: false,
        }

        // ajax로 곡 목록을 가져와 새로운 $tr을 테이블에 append
        fetch("/api/v1/midi", {
            method: "GET"
        })
            .then(res => res.json())
            .then(data => {
                data.forEach((song, i) => {
                    const $tr = document.createElement("tr")
                    $tr.setAttribute("title", song.originalFileName)
                    //` | 업로드 일자: [${song.createdDate.replace(/T/ig, " ")}] | 업로더 아이디: ${song.userId}
                    $tr.setAttribute("genre", song.genre)
                    $tr.setAttribute("songTitle", song.customTitle)
                    $tr.setAttribute("singer", song.singer)
                    $tr.setAttribute("data-id", song.id)
                    $tr.innerHTML = `<th scope="row" class="song-id">${song.id}&nbsp;&nbsp;&nbsp;</th>
                    <td class="song-title"><span class="text-muted">${song.customTitle} - </span> ${song.singer}</td>
                    `
                    document.getElementById("table-info-tbody").appendChild($tr)
                    currentPlay.fullEl.push($tr)
                })

                // 시작시 테이블 뒤집기 설정되어있으면 뒤집기
                if(localStorage.getItem("reverse_order_start") && localStorage.getItem("reverse_order_start") == "yes") {
                    reverseTable()
                }
            })

        // 아이디를 정보로 받아 오디오를 재생하는 함수
        function loadAudio(audioCtx, id) {

            audioCtx.loop = false
            audioCtx.src = "/api/v1/midi/mp3/" + id
            audioCtx.load()
            audioCtx.play()

        }

        // 테이블 뒤집기 (물리)
        function reverseTable() {
            const $trArr = document.querySelectorAll("#table-info tbody tr")
            const $tbody = document.querySelector("#table-info tbody")
            $tbody.innerHTML = ""
            Array.from($trArr).reverse().forEach((el, i) => {
                $tbody.append(el)
            })
        }

        // 테이블 id 순으로 정렬
        function orderTable(isAscendOrder = true) {
            const $trArr = document.querySelectorAll("#table-info tbody tr")
            const $tbody = document.querySelector("#table-info tbody")
            $tbody.innerHTML = ""
            Array.from($trArr)
                .sort((a, b) => isAscendOrder ? b.dataset.id - a.dataset.id : a.dataset.id - b.dataset.id)
                .forEach((el, i) => {
                    $tbody.append(el)
                })
        }

        // 테이블 순서 셔플
        function shuffleTable() {
            const $trArr = document.querySelectorAll("#table-info tbody tr")
            const $tbody = document.querySelector("#table-info tbody")
            $tbody.innerHTML = ""
            Array.from($trArr)
                .map(el => [Math.random(), el])
                .sort((a, b) => a[0] - b[0])
                .map(el => el[1])
                .forEach((el, i) => {
                    $tbody.append(el)
                })
        }

        function copyToClipboard(text) {
            var t = document.createElement("textarea");
            document.body.appendChild(t);
            t.value = text;
            t.select();
            document.execCommand('copy');
            document.body.removeChild(t);
            alert("복사되었습니다.")
        }


        // 제목을 클릭하면 노래가 재생
        document.addEventListener("click", e => {
            if (e.target && e.target.classList.contains("song-title")) {
                const audio = document.getElementById("audio-player")
                const parentEl = e.target.parentElement
                loadAudio(audio, parentEl.dataset.id)
                currentPlay.trEl = parentEl // 현재 재생중인 곡의 tr을 currentPlay.trEl에 저장

                // 플레이어 정보 갱신
                document.getElementById("play-title").innerHTML = parentEl.getAttribute("songTitle")
                document.getElementById("play-singer").innerHTML = parentEl.getAttribute("singer")
                document.getElementById("play-genre").innerHTML = parentEl.getAttribute("genre")
            } else if(e.target && e.target.classList.contains("share-html")) {
                const songId = $(e.target).closest("tr").data("id")
                // console.log(songId)
                const currnentOrigin = window.location.origin
                $("#html-embed-modal").modal("show")
                $("#html-embed-code").text(`<iframe style="width: 500px; height: 336px; border: none;" src="${currnentOrigin}/midi-embed?id=${songId}"></iframe>`)
            }
        })

        $("#btn-copy-html").on("click", e => {
            const text = $("#html-embed-code").val()
            copyToClipboard(text)
        })

        // 곡이 끝나면 (ended) tr.nextSibling으로 다음 곡을 찾아 재생
        document.getElementById("audio-player").addEventListener("ended", e => {
            const audio = e.target
            const nextEl = currentPlay.trEl.nextSibling || document.querySelector("#table-info tbody tr")
            loadAudio(audio, nextEl.dataset.id)
            currentPlay.trEl = nextEl // 현재 재생중인 곡의 tr을 currentPlay.trEl에 저장

            // 플레이어 정보 갱신
            document.getElementById("play-title").innerHTML = parentEl.getAttribute("songTitle")
            document.getElementById("play-singer").innerHTML = parentEl.getAttribute("singer")
            document.getElementById("play-genre").innerHTML = parentEl.getAttribute("genre")
        })

        // shuffle buttton 색깔, 순서 아이콘 관리
        function manageListStatus() {
            const $shuffleButton = $("#btn-shuffle")
            const $orderIcon = $("#order-icon")

            if(currentPlay.isShuffleOn) {
                $shuffleButton.removeClass("btn-dark").addClass("btn-danger")
                $orderIcon.removeClass(["fa-arrow-up", "fa-arrow-down", "fa-random"]).addClass("fa-random")
            } else {
                $shuffleButton.removeClass("btn-danger").addClass("btn-dark")
                $orderIcon.removeClass(["fa-arrow-up", "fa-arrow-down", "fa-random"])
                    .addClass(localStorage.getItem("reverse_order_start") === "yes" ? "fa-arrow-down" : "fa-arrow-up")
            }

        }

        // 내림차순 이벤트
        document.querySelector("#table-info .head-title").addEventListener("click", e => {
            reverseTable()

            if(localStorage.getItem("reverse_order_start") && localStorage.getItem("reverse_order_start") == "yes") {
                localStorage.setItem("reverse_order_start", "no")
            } else {
                localStorage.setItem("reverse_order_start", "yes")
            }
            manageListStatus()
        })

        // 셔플 이벤트
        $("#btn-shuffle").on("click", function(e) {
            if(!currentPlay.isShuffleOn) {
                shuffleTable()
            } else {
                orderTable(localStorage.getItem("reverse_order_start") === "yes")
            }
            currentPlay.isShuffleOn = !currentPlay.isShuffleOn
            manageListStatus()
        })

        function init() {
        }
        init()

    },


}
