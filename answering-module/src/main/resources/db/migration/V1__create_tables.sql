CREATE TABLE message
(
    id                  TEXT NOT NULL,
    group_user          INT  NOT NULL,
    template_id         INT  NOT NULL,
    file_content        BYTEA,
    file_type           VARCHAR(8),
    data_replacement    JSONB,
    error_text          VARCHAR(126),
    message_text        VARCHAR(255),
    created_date        TIMESTAMP NOT NULL,
    status              VARCHAR(16) NOT NULL,
    date_status         TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE group_user
(
    id 	        INT NOT NULL PRIMARY KEY,
    ugroup  INT NOT NULL UNIQUE
);

CREATE TABLE mail_template
(
    id 	        INT NOT NULL PRIMARY KEY,
    template    VARCHAR(200) NOT NULL
);

CREATE TABLE file_type
(
    id 	        INT NOT NULL PRIMARY KEY,
    type        VARCHAR(8) NOT NULL
);
