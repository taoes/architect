FROM node:latest
MAINTAINER zhoutao zhoutao825638@vip.qq.com
COPY . /app
WORKDIR /app
RUN npm install && npm build
EXPOSE 3000
ENTRYPOINT ["npm","dev"]