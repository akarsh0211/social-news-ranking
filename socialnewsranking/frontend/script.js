fetch("http://localhost:8080/trending")
    .then(res => res.json())
    .then(data => {

        const list = document.getElementById("newsList");

        data.forEach(item => {
            const li = document.createElement("li");
            li.innerText = item.title + " - Score: " + item.score;
            list.appendChild(li);
        });
    });
