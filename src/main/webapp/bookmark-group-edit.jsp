<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/vertical_table.css">
</head>
<body>
    <h1>북마크 그룹 수정</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>
    <div class="bge-table-container">
        <table id="vertical-1" class="table table-horizontal table-bordered">
            <tr><th>북마크 이름</th><td><input type="text" id="bookmark_name" name="bookmark_name"></td></tr>
            <tr><th>순서</th><td><input type="text" id="bookmark_order" name="bookmark_order"></td></tr>
        </table>
        <div style="display: flex; flex-direction: column; border-collapse: collapse;">
            <div style="margin: auto">
                <a href="javascript:history.go(-1)">돌아가기</a> | <button onclick="updateBookmark()">수정</button>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="component/footer.jsp"/>
    </div>

    <script>
        let originName = null;
        let originOrder = null;
        const params = new URLSearchParams(window.location.search);
        const bookmarkId = params.get('id');
        const url = encodeURI(`/bookmark?id=${'${bookmarkId}'}`);
        fetch(url, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const bookmark = data;
                const name = document.querySelector('#bookmark_name');
                const order = document.querySelector('#bookmark_order');
                name.setAttribute('value', `${'${bookmark.name}'}`);
                order.setAttribute('value', `${'${bookmark.order}'}`);
                originName = `${'${bookmark.name}'}`;
                originOrder = `${'${bookmark.order}'}`;
            })
            .catch(err => {
                console.log(err)
                alert('북마크 그룹 정보를 가져오는데에 실패했습니다.')
            })

        function updateBookmark() {
            const name = document.querySelector('#bookmark_name').value;
            const order = document.querySelector('#bookmark_order').value;
            if (name === '' || order === '') {
                alert('모든 내용을 입력해주세요')
                return;
            }
            if (isNaN(order)) {
                alert('순서는 숫자로만 입력해주세요')
                return;
            }
            if (name === originName && order === originOrder) {
                if (confirm('수정된 사항이 없습니다. 이전 페이지로 돌아가시겠습니까?')) {
                    history.go(-1);
                }
                return;
            }

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({
                    name: name,
                    order: order
                })
            })
                .then(() => window.location.href='/bookmark-group.jsp')
                .catch(err => {
                    console.log(err)
                    alert('북마크 그룹을 수정하는데에 실패하였습니다.')
                });
        }
    </script>
</body>
</html>
