<B>Переменные окружения задаются в консоле или IDE:</B>

export DB_HOST=localhost<br/>
export DB_USERNAME=postgres<br/>
export DB_PASSWORD=postgres<br/>
export DB_NAME=postgres<br/>
export DB_LOCAL_PORT=5432<br/>
export DB_DOCKER_PORT=5432<br/>
export MQ_USERNAME=rabbitmq<br/>
export MQ_PASSWORD=rabbitmq<br/>
export MQ_LOCAL_PORT=5672<br/>
export MQ_DOCKER_PORT=5672<br/>
export MQ_MESSAGE_QUEUE=message_queue<br/>
export MQ_MAIL_QUEUE=mail_queue<br/>
export MQ_STATUS_QUEUE=status_queue<br/>
export MQ_EXCHANGE=messageExchange<br/>
export MQ_MESSAGE_ROUTING_KEY=messageRoutingKey<br/>
export MQ_MAIL_ROUTING_KEY=mailRoutingKey<br/>
export MQ_STATUS_ROUTING_KEY=statusRoutingKey<br/>
export MQ_HOST=localhost<br/>
export MQ_VIRTUAL_HOST=vhost<br/>

<B>Перейти в папку проекта</B><br/>
cd /d/JAVA_PROJECTS/message-sender-app/docker-compose

<B>Выпонить комманду для создания докер имеджей:</B><br/>
docker-compose up -d
