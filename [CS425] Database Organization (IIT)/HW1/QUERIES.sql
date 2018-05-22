-- 1.	Find all products missing a product model number?

SELECT * FROM Products
WHERE products_model IS NULL;

-- 2.	Find all products with over $500 and less than $699?

SELECT * FROM Products
WHERE products_price BETWEEN 500 AND 699;

-- 3.	Find all products that need to be ordered?

SELECT * FROM Products
WHERE products_ordered = 0 AND products_quantity < 2;

-- 4.	Find all products in Leather Laptop Cases category?

SELECT products.*
FROM products INNER JOIN (categories_description INNER JOIN products_to_categories ON categories_description.categories_id = products_to_categories.categories_id) ON products.products_id = products_to_categories.products_id
WHERE (((categories_description.categories_name)="Leather Laptop Cases"));

-- 5.	Find all Categories with the keyword "women" in the text?

SELECT * FROM Categories 
WHERE categories_keywords LIKE '%women%';

-- 6.	Find all categories added in 2014?

SELECT *
FROM categories
WHERE year( categories.date_added)=2014;

-- 7.	Find all categories added before 2014 and has not been modified?

SELECT * FROM Categories WHERE date_added <= '2014-01-01 00:00' AND last_modified IS NULL;

-- 8.	Find all products to all main categories (main category has no parent)?
-- MYSQL version of a full outer join 

SELECT * FROM Products
LEFT JOIN Products_to_categories ON Products.products_id = Products_to_categories.products_id
LEFT JOIN Categories ON Products_to_categories.categories_id = Categories.categories_id
UNION
SELECT * FROM Products
RIGHT JOIN Products_to_categories ON Products.products_id = Products_to_categories.products_id
LEFT JOIN Categories ON Products_to_categories.categories_id = Categories.categories_id
UNION
SELECT * FROM Products
RIGHT JOIN Products_to_categories ON Products.products_id = Products_to_categories.products_id
RIGHT JOIN Categories ON Products_to_categories.categories_id = Categories.categories_id
WHERE parent_id = 0
;

-- 9.	Find all products that are Tote Bags?

SELECT products.*, categories_description.categories_name
FROM categories, products INNER JOIN (products_to_categories INNER JOIN categories_description ON products_to_categories.categories_id = categories_description.categories_id) ON products.products_id = products_to_categories.products_id
WHERE (((categories_description.categories_name)='Tote Bags'));

-- 10.	List all product models that does not start with word "BAG"

SELECT * FROM Products
WHERE products_model NOT LIKE 'BAG%';
