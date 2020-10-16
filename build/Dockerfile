FROM nginx

WORKDIR /usr/share/nginx/html
COPY web-client .

CMD sed -i 's/listen  .*/listen '80';/g' /etc/nginx/conf.d/default.conf && exec nginx -g 'daemon off;'
