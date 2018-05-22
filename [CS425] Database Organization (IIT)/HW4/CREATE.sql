CREATE TABLE Orders_status (
    status_id int NOT NULL,
    language_id int NOT NULL,
    orders_status_name varchar(100),
    public_flag int NOT NULL,
    downloads_flag int NOT NULL,

    PRIMARY KEY (status_id)
);

CREATE TABLE Addresses (
    id int NOT NULL,
    name varchar(100),
    address_format_id int NOT NULL,
    postcode varchar(100),
    street_address varchar(100),
    state varchar(100),
    city varchar(100),
    country varchar(100),

    PRIMARY KEY (id)
);

CREATE TABLE Customers (
    id int NOT NULL,
    address_id int NOT NULL,
    gender varchar(8),
    firstname varchar(8),
    dob varchar(8),

    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES Addresses(id)
);

CREATE TABLE Orders (
	id int NOT NULL,
    status int NOT NULL,
    billing_address int NOT NULL,
    delivery_address int NOT NULL,
    customer_id int NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (status) REFERENCES Orders_status(status_id),

    FOREIGN KEY (billing_address) REFERENCES Addresses(id),
    FOREIGN KEY (delivery_address) REFERENCES Addresses(id),
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE Products (
    id int NOT NULL,
    quantity int NOT NULL,
    model varchar(100),
    price varchar(100),
    date_added varchar(100),
    last_modified varchar(100),
    weight varchar(100),
    status varchar(100),
    tax_class_id varchar(100),
    manufacturers_id int NOT NULL,
    ordered int NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Products_description (
    products_id int NOT NULL,
    viewed int NOT NULL,
    name varchar(256),
    description varchar(256),

    FOREIGN KEY (products_id) REFERENCES Products(id)
);

CREATE TABLE Orders_products (
    orders_id int NOT NULL,
    products_id int NOT NULL,
    products_quantity int NOT NULL,

    FOREIGN KEY (orders_id) REFERENCES Orders(id),
    FOREIGN KEY (products_id) REFERENCES Products(id)
);

CREATE TABLE Categories(
    id int NOT NULL,
    parent_id int NOT NULL,
    date_added varchar(100),
    last_modified varchar(100),
    sort_order varchar(100),
    keywords varchar(100),

    PRIMARY KEY (id)
);

CREATE TABLE Products_to_categories (
    products_id int NOT NULL,
    categories_id int NOT NULL,

    FOREIGN KEY(products_id) REFERENCES Products(id),
    FOREIGN KEY(categories_id) REFERENCES Categories(id)
);

CREATE TABLE Categories_description (
    categories_id int NOT NULL,
    name varchar(500),
    description varchar(500),

    FOREIGN KEY (categories_id) REFERENCES Categories(id)
);

CREATE UNIQLUE INDEX Orders_status_index ON
Orders_status (status_id);

CREATE UNIQLUE INDEX Addresses_index ON
Addresses (id);

CREATE UNIQLUE INDEX Customers_index ON
Customers (id);

CREATE UNIQLUE INDEX Orders_index ON
Orders (id);

CREATE UNIQLUE INDEX Products_index ON
Products (id);

CREATE UNIQLUE INDEX Products_description_index ON
Products_description (products_id);

CREATE UNIQLUE INDEX Orders_products_index ON
Orders_products (orders_id);

CREATE UNIQLUE INDEX Categories_index ON
Categories (id);

CREATE UNIQLUE INDEX Products_to_categories_index ON
Products_to_categories (products_id, categories_id);