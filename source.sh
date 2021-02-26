printf " -> Try to a connect delete if exists \n\n"
curl \
  --request DELETE \
  --header "Content-Type: application/json" \
  http://localhost:8083/connectors/random-string-source

printf "\n\n -> Create source connector \n\n"
curl \
  --request POST \
  --header "Content-Type: application/json" \
  --data @source.json \
  http://localhost:8083/connectors