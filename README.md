build the image:
docker build -t account-manager:1.0 .

run the container:
docker run -d -p 8080:8080 account-manager:1.0


in the browser open:
http://localhost:8080
