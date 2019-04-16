var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
    console.log('an user connected');
  });

http.listen(8080, function(){
  console.log('listening on *:80');
});

io.on('connection', function(socket){
    socket.broadcast.emit('chat message',"welcome from the server");
    socket.on('chat message', function(msg){
        io.emit('chat message', msg);
    });
  });

