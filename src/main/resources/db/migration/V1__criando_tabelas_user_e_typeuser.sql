-- Authority table
CREATE TABLE authorities (
    id VARCHAR(36) NOT NULL,
    authority VARCHAR(250) NOT NULL UNIQUE,
    observation VARCHAR(250) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- Role table
CREATE TABLE roles (
    id VARCHAR(36) NOT NULL,
    description VARCHAR(250) NOT NULL UNIQUE,
    observation VARCHAR(250) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- Role_Authorities table
CREATE TABLE role_authority (
    role_id VARCHAR(36) NOT NULL,
    authority_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (role_id,authority_id),
    CONSTRAINT fk_role_authority_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id),
    CONSTRAINT fk_role_authority_authority
        FOREIGN KEY (authority_id)
        REFERENCES authorities(id)
);



-- User table
CREATE TABLE users (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE,
    role_id VARCHAR(36),
    PRIMARY KEY (id),
    CONSTRAINT fk_role_user
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
);

-- Indice na coluna de e-mail
CREATE INDEX idx_user_email ON users(email);
