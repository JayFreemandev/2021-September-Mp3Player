const updateMain = {
    init() {
        const self = this
        $(".bg-img.update").on("click", e => {
            const jqTr = $(e.target).closest("tr")
            self.update(jqTr)
        })
        $(".bg-img.delete").on("click", e => {
            const jqTr = $(e.target).closest("tr")
            self.delete(jqTr)
        })
    },
    update(jqTr) {

        const id = jqTr.find(".id").text()
        const data = {
            singer: jqTr.find("input.singer").val(),
            customTitle: jqTr.find("input.custom-title").val(),
            genre: jqTr.find("input.genre").val()
        }


        $.ajax({
            type: "PUT",
            url: "/api/v1/midi/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(res => {
            console.log(res)
            if (res != -99) {
                alert("정보가 수정되었습니다. id: " + res)
            } else {
                alert("오류가 발생했습니다.")
            }
        }).fail(err => {
            alert(JSON.stringify(err))
            console.log(err)
        })
    },

    delete(jqTr) {

        const id = jqTr.find(".id").text()
        if (!confirm("정말 이 파일을 삭제하시겠습니까?")) {
            return false
        }

        $.ajax({
            type: "DELETE",
            url: "/api/v1/midi/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(res => {
            console.log(res)
            if (res != -99) {
                alert(`${res}번 파일이 삭제되었습니다.`)
                jqTr.remove()
            } else {
                alert("오류가 발생했습니다.")
            }
        }).fail(err => {
            alert(JSON.stringify(err))
            console.log(err)
        })

    }
}
updateMain.init()
