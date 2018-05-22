CREATE TABLE Orders_status (
    orders_status_id int NOT NULL,
    language_id int NOT NULL,
    orders_status_name varchar(100),
    public_flag int NOT NULL,
    downloads_flag int NOT NULL,

    PRIMARY KEY (orders_status_id)
);


CREATE TABLE Order_billing_address (
    orders_billing_address_id int NOT NULL,
    billing_address_format_id int not NULL,
    billing_postcode varchar(100),
    billing_country varchar(100),
    billing_city varchar(100),
    billing_street_address varchar(100),
    billing_state varchar(100),
    payment_method varchar(100),

    PRIMARY KEY (orders_billing_address_id)
);

CREATE TABLE Order_delivery_address (
    orders_delivery_address_id int NOT NULL,
    delivery_name varchar(100),
    delivery_address_format_id varchar(100),
    delivery_state varchar(100),
    delivery_country varchar(100),
    delivery_postcode varchar(100),
    delivery_city varchar(100),
    delivery_street_address varchar(100),

    PRIMARY KEY (orders_delivery_address_id)
);

CREATE TABLE Customers_address (
    address_id int NOT NULL,
    customers_name varchar(100),
    customers_address_format_id int NOT NULL,
    customers_postcode varchar(100),
    customers_street_address varchar(100),
    customers_state varchar(100),
    customers_city varchar(100),
    customers_country varchar(100),

    PRIMARY KEY (address_id)
);

CREATE TABLE Customers (
    customers_id int NOT NULL,
    customers_address_id int NOT NULL,

    customers_gender varchar(8),
    customers_firstname varchar(8),
    customers_dob varchar(8),

    PRIMARY KEY (customers_id),
    FOREIGN KEY (customers_address_id) REFERENCES Customers_address(address_id)
);

CREATE TABLE Orders (
	orders_id int NOT NULL,
    orders_status int NOT NULL,
    orders_billing_address int NOT NULL,
    orders_delivery_address int NOT NULL,
    order_customer_id int NOT NULL,

    PRIMARY KEY (orders_id),
    FOREIGN KEY (orders_status) REFERENCES Orders_status(orders_status_id),
    FOREIGN KEY (orders_billing_address) REFERENCES Order_billing_address(orders_billing_address_id),
    FOREIGN KEY (orders_delivery_address) REFERENCES Order_delivery_address(orders_delivery_address_id),
    FOREIGN KEY (order_customer_id) REFERENCES Customers(customers_id)
);

CREATE TABLE Products (
    products_id int NOT NULL,

    products_quantity int NOT NULL,
    products_model varchar(100),
    products_price varchar(100),
    products_date_added varchar(100),
    products_last_modified varchar(100),
    products_weight varchar(100),
    products_status varchar(100),
    products_tax_class_id varchar(100),
    manufacturers_id int NOT NULL,
    products_ordered int NOT NULL,

    PRIMARY KEY (products_id)
);

CREATE TABLE Products_description (
    description_id int NOT NULL,
    products_id int NOT NULL,
    products_viewed int NOT NULL,
    products_name varchar(256),

    PRIMARY KEY (description_id),
    FOREIGN KEY (products_id) REFERENCES Products(products_id)
);

CREATE TABLE Orders_products (
	orders_products_id int NOT NULL,
    orders_id int NOT NULL,
    products_id int NOT NULL,
    products_quantity int NOT NULL,

	PRIMARY KEY (orders_products_id),
    FOREIGN KEY (orders_id) REFERENCES Orders(orders_id),
    FOREIGN KEY (products_id) REFERENCES Products(products_id)
);

CREATE TABLE Categories(
    categories_id int NOT NULL,
    parent_id int NOT NULL,
    date_added varchar(100),
    last_modified varchar(100),
    sort_order varchar(100),
    categories_keywords varchar(100),

    PRIMARY KEY (categories_id)

);

CREATE TABLE Products_to_categories (
    relation_id int NOT NULL,
    products_id int NOT NULL,
    categories_id int NOT NULL,

    PRIMARY KEY (relation_id),
    FOREIGN KEY(products_id) REFERENCES Products(products_id),
    FOREIGN KEY(categories_id) REFERENCES Categories(categories_id)
);

CREATE TABLE Categories_description (
    description_id int NOT NULL,
    categories_id int NOT NULL,
    categories_name varchar(500),

    PRIMARY KEY (description_id),
    FOREIGN KEY (categories_id) REFERENCES Categories(categories_id)
);

