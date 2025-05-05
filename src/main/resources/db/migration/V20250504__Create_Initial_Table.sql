CREATE TABLE users
(
    id       character varying(255) NOT NULL,
    email    character varying(100) NOT NULL,
    name     character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    role     character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER':: character varying, 'ADMIN':: character varying])::text[])
) )
);

CREATE TABLE tasks
(
    id          character varying(255) NOT NULL,
    description character varying(255),
    due_date    date,
    status      character varying(255),
    title       character varying(100) NOT NULL,
    id_user     character varying(255),
    CONSTRAINT tasks_status_check CHECK (((status)::text = ANY ((ARRAY['TODO':: character varying, 'IN_PROGRESS':: character varying, 'DONE':: character varying])::text[])
) )
);

--
-- =====================================================Unique Constraint=====================================================
ALTER TABLE ONLY users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

--
-- =====================================================Primary Key=====================================================
ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);

--
-- =====================================================Foreign Key=====================================================
ALTER TABLE ONLY tasks
    ADD CONSTRAINT fkl78vfuynuac03yavfpfru3sb9 FOREIGN KEY (id_user) REFERENCES public.users(id);
