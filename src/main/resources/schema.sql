create table booking(

    id int auto_increment,
    property_id int NOT NULL,
    user_id int NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL

);

create table block(

    id int auto_increment,
    property_id int NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL

);