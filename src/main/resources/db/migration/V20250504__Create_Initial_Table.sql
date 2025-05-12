CREATE TABLE s_password
(
    id_user  character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);

CREATE TABLE s_roles
(
    id   character varying(100) NOT NULL,
    name character varying(255) NOT NULL
);

CREATE TABLE s_users
(
    id      character varying(255) NOT NULL,
    email   character varying(100) NOT NULL,
    name    character varying(100) NOT NULL,
    id_role character varying(255)
);

CREATE TABLE task
(
    id          character varying(255) NOT NULL,
    description character varying(255),
    due_date    date,
    status      character varying(255),
    title       character varying(100) NOT NULL,
    id_user     character varying(255),
    CONSTRAINT task_status_check CHECK (((status)::text = ANY (ARRAY[('TODO':: character varying)::text, ('IN_PROGRESS':: character varying)::text, ('DONE':: character varying)::text])
) )
);


--
-- ======================================================UNIQUE CONSTRAINT==============================================
ALTER TABLE ONLY s_users
    ADD CONSTRAINT uk2lcv26kt28p27enwkw01c2s1g UNIQUE (email);


--
-- =================================================CONSTRAINT PRIMARY KEY==============================================
ALTER TABLE ONLY s_password
    ADD CONSTRAINT s_password_pkey PRIMARY KEY (id_user);

ALTER TABLE ONLY s_roles
    ADD CONSTRAINT s_roles_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_users
    ADD CONSTRAINT s_users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);

--
-- =================================================CONSTRAINT FOREIGN KEY==============================================
ALTER TABLE ONLY s_users
    ADD CONSTRAINT fk4k103cqcehdbobgrydgsa44gu FOREIGN KEY (id_role) REFERENCES s_roles(id);


ALTER TABLE ONLY s_password
    ADD CONSTRAINT fk7wur1l3rdr9u3msft1aluptkl FOREIGN KEY (id_user) REFERENCES s_users(id);

ALTER TABLE ONLY task
    ADD CONSTRAINT fkjvutyaaubcbmkqkh2gx7gk8br FOREIGN KEY (id_user) REFERENCES s_users(id);
