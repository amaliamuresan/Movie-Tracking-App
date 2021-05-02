# Movie-Tracking-App

## Server api requests

### User request 

1.Login<br/>
- ``` https://localhost:8080/api/users/login ``` <br/>
- Request - POST<br/>
```json
{
  "email":"email",
  "password":"password"
}
```
- Response
```json
{
    "uid": "user_uid",
    "token": "generated_login_token"
}
```

2.Register<br/>
- ``` https://localhost:8080/api/users/register ``` <br/>
- Request - POST<br/>
```json
{
  "email":"email",
  "password":"password"
}
```
- Response
```json
{
    "uid": "user_uid"
}
```

3.Get user details<br/>
- ``` https://localhost:8080/api/users/{uid} ``` <br/>
- Request - GET<br/>
```json
No request body
```
- Response
```json
Example
This will return all not Null user fields
{
    "name": "Name",
    "password": "password",
    "email": "email@gmail.com",
    "username": "username",
    "uid": "user_uid",
    "to_watch_movies": [
        "618353",
        "732450",
        "14214124",
        "6183532"
    ],
    "watched_movies": [
        "618353"
    ]
}
```
4.Add to watch movies<br/>
- ``` https://localhost:8080/api/users/add_to_watch_movie``` <br/>
- Request - POST<br/>
```json
{
  "uid" : "user_uid",
  "movie_id" : "tmdb_movie_id",
  "token": "user_login_token"
}
```
- Response
```json
On success
{
    "Success": "Movie added successfully to watchlist"
}
```
5.Add watched movies<br/>
- ``` https://localhost:8080/api/users/add_watched_movie``` <br/>
- Request - POST<br/>
```json
{
  "uid" : "user_uid",
  "movie_id" : "tmdb_movie_id",
  "token": "user_login_token"
}
```
- Response
```json
On success
{
    "Success": "Movie added successfully"
}
```
6.Remove to watch movies<br/>
- ``` https://localhost:8080/api/users/remove_to_watch_movie``` <br/>
- Request - POST<br/>
```json
{
  "uid" : "user_uid",
  "movie_id" : "tmdb_movie_id",
  "token": "user_login_token"
}
```
- Response
```json
On success
{
    "Success": "Movie removed successfully"
}
```
7.Remove watched movies<br/>
- ``` https://localhost:8080/api/users/remove_watched_movie``` <br/>
- Request - POST<br/>
```json
{
  "uid" : "user_uid",
  "movie_id" : "tmdb_movie_id",
  "token": "user_login_token"
}
```
- Response
```json
On success
{
    "Success": "Movie removed successfully"
}
```
8.Get to watch movies<br/>
- ``` https://localhost:8080/api/users/get_to_watch_movie``` <br/>
- Request - POST<br/>
```json
{
  "logged_uid" : "logged_user_uid",
  "user_uid" : "requested_user_uid",
  "token" : "logged_user_login_token"
}
```
- Response
```json
Returns a list of json objects conainting all movies minimal information
Example
[
    {
        "poster_path": "https://image.tmdb.org/t/p/w500/k8Q9ulyRE8fkvZMkAM9LPYMKctb.jpg",
        "id": "618353",
        "title": "Batman: Death in the Family",
        "genres": [
            "Animation",
            "Action"
        ]
    },
    {
        "poster_path": "https://image.tmdb.org/t/p/w500/uDhnTtSxU5a8DtZdbbin3aZmkmU.jpg",
        "id": "732450",
        "title": "Batman: Soul of the Dragon",
        "genres": [
            "Animation",
            "Action",
            "Adventure",
            "Crime",
            "Fantasy"
        ]
    }
]
```
9.Get watched movies<br/>
- ``` https://localhost:8080/api/users/get_watched_movie``` <br/>
- Request - POST<br/>
- Everything else the same as above

### Movie requests
1.Discover movies by popularity
- ``` https://localhost:8080/api/tmdb/discover_movie?type=popularity&genre=Action&page=2 ``` <br/>
``` 
type=popularity (always, required)
genre={Genre} (optional) select genre from genres list
page={pageNo} (optional) select page number
```
2.Search movies by title
- ``` https://localhost:8080/api/tmdb/search_movie?title=Batman``` <br/>
``` 
title={MovieTitle} (required)
```
3.Get extended information about a movie
- ```https://localhost:8080/api/tmdb/get_movie?id=732450 ```<br/>
```
id={MovieId} (required) tmdb movie id
```
4.Get genres list
- ```https://localhost:8080/api/tmdb/get_genres```<br/>
