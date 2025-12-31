-- 1. productsテーブルへの挿入（stockカラムは削除する）
INSERT INTO products (id, name, model_number, manufacturer, price, description, image_url) VALUES 
(1, '4K液晶テレビ 50V型', '50X-8000H', 'SONY', 89800, '高画質プロセッサー搭載。', 'https://placehold.jp/300x200.png?text=TV'),
(2, 'ドラム式洗濯機 11kg', 'BD-SV110E', 'HITACHI', 158000, 'AIお洗濯でかしこく。', 'https://placehold.jp/300x200.png?text=Washer'),
(3, 'スチームオーブンレンジ', 'ER-VD3000', 'TOSHIBA', 45000, '石窯ドーム構造。', 'https://placehold.jp/300x200.png?text=Oven'),
(4, 'コードレス掃除機', 'SV18 FF', 'Dyson', 58000, '軽量なのにパワフル。', 'https://placehold.jp/300x200.png?text=Cleaner');

-- 2. stocksテーブルへの挿入（product_id で紐付ける）
-- id=1の商品に在庫10個、id=2に5個... という設定です
INSERT INTO stocks (product_id, quantity) VALUES 
(1, 10),
(2, 5),
(3, 8),
(4, 12);