/*SQL Assignment 2*/
/*Markiyan Varhola*/
/*A20324717*/

/*Questions: */

/*1. Find all customers without orders?*/

SELECT * FROM customers WHERE customers_id NOT IN (SELECT customers_id FROM orders);

/*2. Find all customers not in the US?*/

SELECT * FROM customers
WHERE customers.customers_id 
IN (SELECT customers_id FROM orders
    WHERE customers_country = "US" or customers_country = "United States");

/*3. Find the ids of all customers who subscribe to newsletter emails*/

SELECT customers_id FROM customers WHERE customers_newsletter = 1;

/*4. a) To find the total number of orders made by female customers*/

SELECT customers_name, COUNT(customers_name) AS count
FROM orders
WHERE orders.customers_id IN (SELECT customers_id FROM customers WHERE customers_gender="f" )
GROUP BY customers_name

UNION ALL

SELECT 'SUM' customers_id, COUNT(customers_id)
FROM orders;

/*b) To find the total amount for each customers that are female*/ 

SELECT customers_name, (shipping_cost+products_price) as total FROM(
    SELECT s.*, p.products_price
    FROM( SELECT orders.customers_name, orders.customers_id, orders.orders_id, orders.shipping_cost
		  FROM orders
		  WHERE orders.customers_id IN (SELECT customers_id FROM customers WHERE customers_gender="f" ))s, Products p
            WHERE p.products_id IN (SELECT products_id FROM orders_products WHERE orders_products.orders_id = s.orders_id));


/*5. Group orders by category of products ordered and list (category name , order total, products ordered and date of orders)*/

SELECT i.categories_name,i.products_ordered, o.date_purchased, (i.shipping_cost+i.products_price) as order_total
FROM (SELECT s.* ,cd.categories_name
	FROM (SELECT orders_products.products_id, Products.products_ordered, orders.shipping_cost, Products.products_price,orders.orders_id 
            FROM orders_products, orders, Products 
            WHERE orders_products.orders_id = orders.orders_id and orders_products.products_id = Products.products_id)s, categories_description cd 
	WHERE cd.categories_id IN (SELECT categories_id 
                                FROM products_to_categories WHERE products_id = s.products_id))i, orders o
WHERE o.orders_id = i.orders_id
GROUP BY categories_name

/*6. Find all customers with orders?*/

SELECT * FROM customers WHERE customers_id IN (SELECT customers_id FROM orders);

/*7. Find all customer names and addresses for those customers with orders having t a pending status?*/

SELECT customers_name, concat(customers_street_address,customers_city,',',customers_state,',',customers_country,',',customers_postcode)
FROM orders WHERE orders_status = (SELECT orders_status_id FROM orders_status WHERE orders_status_name = "Pending");

/*8. Find the total shipping cost for orders that have been shipped to the US or United Kingdom?*/

SELECT SUM(shipping_cost) FROM orders WHERE orders.delivery_country = "United States" or orders.delivery_country = "United Kingdom";

/*9. Find the total for all orders shipped to international destination?*/

SELECT SUM(total) total
    FROM (SELECT SUM(shipping_cost) AS total
            FROM orders WHERE orders.delivery_country != "United States"
            UNION ALL
            SELECT SUM(products_price) FROM Products
            WHERE products_id IN (SELECT products_id FROM orders_products WHERE orders_id IN (SELECT orders_id FROM orders WHERE orders.delivery_country != "United States")));

/*10. Write your own query that span multiple tables?*/

SELECT * FROM customers
WHERE customers.customers_id 
IN (SELECT customers_id FROM orders
    WHERE customers_country = "US" or customers_country = "United States");

/*11. Generate a mailing list for the companies newsletter.*/

SELECT DISTINCT o.customers_name
FROM orders o, customers c
WHERE c.customers_newsletter and o.customers_id = c.customers_id 



