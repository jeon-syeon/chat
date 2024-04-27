<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delivery Buddy</title>
    <!-- CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>

    <div v-if="!usernameEntered">
        <h2>사용자 이름 입력</h2>
        <form @submit.prevent="submitUsername">
            <div class="form-group">
                <label for="usernameInput">사용자 이름:</label>
                <input type="text" class="form-control" id="usernameInput" v-model="username" required>
            </div>
            <button type="submit" class="btn btn-primary">입장</button>
        </form>
    </div>

    <div v-else>
        <div class="row">
            <div class="col-md-12">
                <h3>게시글 작성</h3>
                <form @submit.prevent="createPost">
                    <div class="form-group">
                        <label for="postTitle">게시글 제목:</label>
                        <input type="text" class="form-control" id="postTitle" v-model="postTitle" required>
                    </div>
                    <div class="form-group">
                        <label for="postContent">게시글 내용:</label>
                        <textarea class="form-control" id="postContent" v-model="postContent" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">게시</button>
                </form>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-12">
                <h3>게시글 목록</h3>
                <ul class="list-group">
                    <li class="list-group-item list-group-item-action" v-for="post in posts" :key="post.id">
                        <strong>{{ post.title }}</strong>
                        <p>{{ post.content }}</p>
                        <button class="btn btn-secondary" @click="startChat(post)">채팅하기</button>
                    </li>
                </ul>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-12">
                <h3>내 채팅방 목록</h3>
                <ul class="list-group">
                    <li class="list-group-item list-group-item-action" v-for="chatRoom in myChatRooms" :key="chatRoom.roomId">
                        <strong>{{ chatRoom.title }}</strong>
                        <p>{{ chatRoom.description }}</p>

                    </li>
                </ul>
            </div>
        </div>


    </div>

</div>

<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            username: localStorage.getItem('username') || '', // 로컬 스토리지에서 사용자 이름 가져오기
            usernameEntered: false,
            postTitle: '',
            postContent: '',
            posts: [],
            myChatRooms: []
        },
        created: function() {
            this.findAllPosts();
            this.loadMyChatrooms();
        },
        methods: {
            submitUsername: function() {
                if (this.username.trim() === '') {
                    alert('사용자 이름을 입력해 주세요.');
                    return;
                }
                this.usernameEntered = true;
                localStorage.setItem('username', this.username.trim());
            },

            findAllPosts: function() {
                axios.get('/api/posts')
                    .then(response => {
                        this.posts = response.data;
                    })
                    .catch(error => {
                        console.error('게시글을 불러오는 데 실패했습니다.', error);
                    });
            },

            loadMyChatrooms: function (userinfo){
                axios.get('/api/my_chatrooms',{
                    params: {
                        username: this.username
                    }
                    })
                    .then(response =>{
                        this.myChatRooms = response.data;
                    })
                    .catch(error =>{
                        console.error('채팅방을 불러오는 데 실패했습니다',error);
                    })
            },

            createPost: function() {
                if (this.postTitle.trim() === '' || this.postContent.trim() === '') {
                    alert('게시글 제목과 내용을 입력해 주세요.');
                    return;
                }
                axios.post('/api/posts',
                    { title: this.postTitle, content: this.postContent, author: this.username })
                    .then(response => {
                        alert('게시글이 생성되었습니다.');
                        this.postTitle = '';
                        this.postContent = '';
                        this.findAllPosts();
                        this.loadMyChatrooms();
                    })
                    .catch(error => {
                        alert('게시글 생성에 실패하였습니다.');
                    });
            },

            startChat: function(post) {
                if (post.author === this.username) {
                    alert('본인과 채팅할 수 없습니다.');
                    return;
                }
                // 게시글 작성자와 사용자가 함께 참여하는 채팅방 생성 요청
                axios.post('/chat/room/create',
                    { title: post.title, sender: this.username, recipient: post.author })
                    .then(response => {
                        console.log("sibal");
                        let roomId = response.data.roomId;
                        localStorage.setItem('wschat.roomId', roomId);
                        // 현재 사용자를 발신자로 설정
                        localStorage.setItem('wschat.sender', this.username);
                        // 채팅방으로 이동
                        location.href = '/chat/room/enter/' + roomId;

                    })
                    .catch(error => {
                        alert('채팅방 생성에 실패하였습니다.');
                    });
            }


        }
    });
</script>
</body>
</html>