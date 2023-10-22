SELECT * FROM u154601261_softybakery.products;

ALTER TABLE products add column IsEnable tinyint;
ALTER TABLE products drop column isEnable;

update products set isenable = true where productid > 0;