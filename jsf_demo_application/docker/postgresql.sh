docker run --rm  \
	--name pg_app_demo --network demo_app \
	-e POSTGRES_DB=jsf_application_demo \
	-e POSTGRES_USER=sa \
	-e POSTGRES_PASSWORD=123 \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-p 5432:5432 \
	-v /opt/dockerdata/postgres:/var/lib/postgresql/data \
	-d postgres