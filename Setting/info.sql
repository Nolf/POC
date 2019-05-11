CREATE TABLE info
(
  id serial NOT NULL,
  name character(20),
  job character(20),  
  CONSTRAINT id_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE info
  OWNER TO postgres;