CREATE TABLE address(
  address_id SERIAL PRIMARY KEY,
  address_type VARCHAR(11) CHECK (address_type IN ('RESIDENCIAL', 'COMERCIAL')),
  public_area VARCHAR(30) NOT NULL,
  residence_number VARCHAR(5) NOT NULL,
  complement VARCHAR(30) NOT NULL,
  neighborhood VARCHAR(30) NOT NULL,
  city VARCHAR(30) NOT NULL,
  state VARCHAR(30) NOT NULL,
  country VARCHAR(30) NOT NULL,
  zipcode VARCHAR(9) NOT NULL,
  point_of_reference VARCHAR(30) NOT NULL,
  main_address BOOLEAN NOT NULL DEFAULT FALSE,
  client_id INT NOT NULL,
  register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  register_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(client_id) REFERENCES client(client_id)
);