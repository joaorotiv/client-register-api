CREATE TABLE client(
  client_id SERIAL PRIMARY KEY,
  name VARCHAR(40) NOT NULL,
  email VARCHAR(30) UNIQUE NOT NULL,
  client_type VARCHAR(10) CHECK (client_type IN ('FISICO', 'JURIDICO')),
  document_type VARCHAR(4) CHECK (document_type IN ('CPF', 'CNPJ')),
  document_number VARCHAR(18) UNIQUE NOT NULL,
  address VARCHAR(80) NOT NULL,
  purchase_permission BOOLEAN NOT NULL,
  register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  register_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);