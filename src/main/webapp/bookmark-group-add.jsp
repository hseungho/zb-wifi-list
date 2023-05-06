<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/vertical_table.css">
</head>
<body>
    <h1>북마크 그룹 추가</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>

    <div class="bga-table-container">
        <table id="vertical-1" class="table table-horizontal table-bordered">
            <tr><th>북마크 이름</th><td><input type="text" id="bookmark_name" name="bookmark_name"></td></tr>
            <tr><th>순서</th><td><input type="text" id="bookmark_order" name="bookmark_order"></td></tr>
        </table>
        <div style="display: flex; flex-direction: column; border-collapse: collapse;">
            <div style="margin: auto">
                <button onclick="saveBookmark()">추가</button>
            </div>
        </div>
    </div>

    <script>
        function saveBookmark() {
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

            const url = encodeURI('/bookmark');
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({
                    name: name,
                    order: order
                })
            })
                .then(() => window.location.href='/bookmark-group.jsp')
                .catch(err => alert(err));
        }
    </script>
</body>
</html>
