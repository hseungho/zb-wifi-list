<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/horizontal_table.css">
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>
    <table>
        <thead>
        <tr style="height: 30px">
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <!-- HISTORY DATA SEGMENT -->
        </tr>
        </tbody>
    </table>
    <div class="footer">
        <jsp:include page="component/footer.jsp"/>
    </div>

    <script>
        fetch('/history', {method:'GET'})
            .then(response => response.json())
            .then(data => {
                const histories = data;
                console.log(histories)
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                if (histories !== null && histories.length > 0) {
                    histories.forEach(history => {
                        const tr = document.createElement('tr');
                        tr.innerHTML = `
                          <td>${'${history.id}'}</td>
                          <td>${'${history.lat}'}</td>
                          <td>${'${history.lnt}'}</td>
                          <td>${'${history.createdAt}'}</td>
                          <td><center>
                            <button data-lat='${'${history.lat}'}' data-lnt='${'${history.lnt}'}' onclick='useHistory()'>조회</button>
                            &nbsp;
                            <button data-id='${'${history.id}'}' onclick='deleteHistory()'>삭제</button>
                          </center></td>
                        `;
                        tbody.appendChild(tr);
                    });
                } else {
                    const tr = document.createElement('tr');
                    tr.innerHTML = "<td colspan='5' style='text-align: center;'>아직 위치 히스토리가 없습니다.</td>";
                    tbody.appendChild(tr);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('위치 히스토리 정보를 가져오는 중 오류가 발생했습니다.');
            });

        function useHistory() {
            const lat = event.target.dataset.lat;
            const lnt = event.target.dataset.lnt;
            window.location.href=`/index.jsp?lat=${'${lat}'}&lnt=${'${lnt}'}&page=1`;
        }

        function deleteHistory() {
            const id = event.target.dataset.id;
            const url = encodeURI(`/history?id=${'${id}'}`);
            fetch(url, { method: 'DELETE' })
                .then((res) => {
                    if (res.ok) {
                        window.location.reload();
                    } else {
                        if (res.status === 400) {
                            alert('잘못된 요청입니다.')
                        } else {
                            alert(`요청에 실패하였습니다.\nSTATUS: ${'${res.status}}'}`)
                        }
                    }
                })
                .catch(err => {
                    console.log(err)
                    alert('위치 히스토리를 삭제하는데에 실패하였습니다.')
                });
        }
    </script>
</body>
</html>
