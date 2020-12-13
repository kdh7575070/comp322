CREATE TABLE cluster (
  cluster int,
  account_id int,
  PRIMARY KEY (account_id)
);



-- knumovie=# \copy cluster(Cluster, account_id) from '~/DB_project/ml-1m/account_cluster.csv' DELIMITER ',' csv header;
-- COPY 151