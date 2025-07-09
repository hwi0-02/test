<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>책 상세페이지</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; background-color: #fff; color: #333; }
    .book-detail { display: flex; max-width: 800px; margin: 0 auto; border: 1px solid #ddd; padding: 20px; }
    .cover img { max-width: 200px; height: auto; border: 1px solid #ccc; }
    .info { margin-left: 20px; flex-grow: 1; }
    .info h1 { font-size: 1.5em; margin-bottom: 10px; }
    .info .author, .info .price { font-size: 1.1em; margin: 5px 0; }
    .info .price { color: #d32f2f; font-weight: bold; }
    .info .description { margin-top: 15px; line-height: 1.4; }
    .buttons { margin-top: 20px; }
    .buttons button { padding: 10px 20px; font-size: 1em; cursor: pointer; border-radius: 4px; margin-right: 10px; }
    .buttons .add-to-cart { background: #fff; color: #333; border: 1px solid #333; }
    .buttons .buy-now { background: #0078c6; color: #fff; border: none; }
    .buttons button:hover { opacity: 0.9; }
  </style>
</head>
<body>
  <div class="book-detail">
    <div class="cover">
      <img src="${book.coverUrl}" alt="책 표지">
    </div>
    <div class="info">
      <h1>${book.title}</h1>
      <p class="author">저자: ${book.author}</p>
      <p class="price">${book.price}원</p>
      <p class="description">${book.description}</p>
      <div class="buttons">
        <button class="add-to-cart">장바구니</button>
        <button class="buy-now">바로 구매</button>
      </div>
    </div>
  </div>
</body>
</html>
