<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/horizontal_table.css">
</head>
<body>
    <h1>북마크 그룹</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>
    <button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
    <table>
        <thead>
        <tr style="height: 30px">
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
            <!-- BOOKMARK DATA SEGMENT -->
        </tbody>
    </table>

    <script>
        const bookmarkListFetchUrl = encodeURI('/bookmark/list');
        fetch(bookmarkListFetchUrl, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const bookmarkList = data;
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                if (bookmarkList !== null && bookmarkList.length > 0) {
                    bookmarkList.forEach(bookmark => {
                        const tr = document.createElement('tr');
                        tr.innerHTML = `
                            <td>${'${bookmark.id}'}</td>
                            <td>${'${bookmark.name}'}</td>
                            <td>${'${bookmark.order}'}</td>
                            <td>${'${bookmark.createdAt}'}</td>
                            <td>${'${bookmark.updatedAt}'}</td>
                            <td><center>
                                <a href="/bookmark-group-edit.jsp?id=${'${bookmark.id}'}">수정</a>
                                &nbsp;
                                <a href="" data-id=${'${bookmark.id}'} onclick="deleteBookmark()">삭제</a>
                            </center></td>
                        `;
                        tbody.appendChild(tr);
                    });
                } else {
                    const tr = document.createElement('tr');
                    tr.innerHTML = "<td colspan='6' style='text-align: center;'>아직 북마크 그룹이 없습니다.</td>";
                    tbody.appendChild(tr);
                }
            })
            .catch(err => {
                console.log(err)
                alert(err)
            });

        function deleteBookmark() {
            if (!confirm('북마크 그룹을 삭제하면 북마크한 WIFI 정보도 삭제됩니다.\n정말 삭제하시겠습니까?')) {
                return;
            }
                const bookmarkId = event.target.dataset.id;
                const url = encodeURI(`/bookmark?id=${'${bookmarkId}'}`);
                fetch(url, { method: 'DELETE' })
                    .then(() => window.location.reload())
                    .catch(err => console.log(err));
        }
    </script>
</body>
</html>
