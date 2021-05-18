import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/add_watched.dart';
import 'package:movie_app/actions/to_watch.dart';
import 'package:movie_app/containers/movie_container.dart';
import 'package:movie_app/containers/user_container.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';

class MoviePage extends StatelessWidget {
  AppUser user;

  @override
  Widget build(BuildContext context) {
    print('MoviePage');
    print(StoreProvider.of<AppState>(context).state.authState.user.toWatchMovies);
    print('\n\n\n\n\n\n\n\n');
    //print(StoreProvider.of<AppState>(context).state.moviesState.movie);
    return Scaffold(
      body: MovieContainer(builder: (BuildContext context, Movie movie) {
        return SingleChildScrollView(
          child: Center(
            child: (movie != null)
                ? Column(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Stack(
                        children: [
                          Container(
                            decoration: BoxDecoration(
                              color: Colors.transparent,
                              image: DecorationImage(
                                fit: BoxFit.fill,
                                image: NetworkImage((movie.backdrop_path != null)
                                    ? movie.backdrop_path
                                    : "https://chameleonfurniture.ro/wp-content/uploads/2020/11/chameleon-furniture-materiale-placi-pal-melaminat-graphite-grey-2162-pe-cover.jpg"),
                              ),
                            ),
                            height: 260,
                          ),
                          Container(
                            height: 260,
                            decoration: BoxDecoration(
                              color: Colors.white,
                              gradient: LinearGradient(
                                begin: FractionalOffset.topCenter,
                                end: FractionalOffset.bottomCenter,
                                colors: [
                                  Colors.grey.withOpacity(0.0),
                                  Colors.black,
                                ],
                                stops: [0.0, 1.0],
                              ),
                            ),
                          ),
                          Positioned(
                            bottom: 16,
                            left: 16,
                            child: Column(
                              children: [
                                Text(
                                  (movie.title != null) ? movie.title : "Movie Title",
                                  style: TextStyle(
                                    fontSize: 28,
                                    fontWeight: FontWeight.w500,
                                    color: Colors.white,
                                  ),
                                ),
                                SizedBox(
                                  height: 6,
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                      UserContainer(builder: (BuildContext context, AppUser user) {
                        return Row(
                          children: [
                            SizedBox(
                              width: 8,
                            ),
                            GestureDetector(
                              onTap: () {
                                StoreProvider.of<AppState>(context).dispatch(AddWatched(movie.id, user));
                              },
                              child: Chip(
                                labelPadding: EdgeInsets.all(2.0),
                                avatar: CircleAvatar(
                                  child: Icon(
                                    Icons.visibility,
                                    color: Colors.white,
                                  ),
                                  backgroundColor: Color.fromRGBO(238, 108, 77, 1),
                                ),
                                label: Padding(
                                  padding: const EdgeInsets.symmetric(horizontal: 3),
                                  child: Text(
                                    (user.watchedMovies != null && user.watchedMovies.contains(movie.id))
                                        ? 'Added'
                                        : 'Watched',
                                    style: TextStyle(
                                      color: Colors.white,
                                    ),
                                  ),
                                ),
                                backgroundColor: Color.fromRGBO(238, 108, 77, 1),
                              ),
                            ),
                            SizedBox(width: 6),
                            GestureDetector(
                              onTap: () {
                                StoreProvider.of<AppState>(context).dispatch(ToWatch(movie.id, user));
//                          (context as Element).markNeedsBuild();
                              },
                              child: Chip(
                                labelPadding: EdgeInsets.all(2.0),
                                avatar: CircleAvatar(
                                  child: Icon(
                                    Icons.add_circle,
                                    color: Colors.white,
                                  ),
                                  backgroundColor: Color.fromRGBO(29, 52, 97, 1),
                                ),
                                label: Padding(
                                  padding: const EdgeInsets.symmetric(horizontal: 3),
                                  child: Text(
                                    (user.toWatchMovies != null && user.toWatchMovies.contains(movie.id))
                                        ? 'Added'
                                        : 'To watch',
                                    style: TextStyle(
                                      color: Colors.white,
                                    ),
                                  ),
                                ),
                                backgroundColor: Color.fromRGBO(29, 52, 97, 1),
                              ),
                            ),
                            Spacer(),
                            Container(
                              decoration: BoxDecoration(
                                  color: Color.fromRGBO(245, 197, 24, 1),
                                  borderRadius: BorderRadius.all(Radius.circular(5))),
                              //color: Color.fromRGBO(245,197,24, 1),
                              child: Padding(
                                padding: const EdgeInsets.all(6.0),
                                child: Text(
                                  'IMDb',
                                  style: TextStyle(
                                    fontSize: 20,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 8),
                              child: Text(
                                '${movie.imdbRating}/10',
                                style: TextStyle(
                                  fontSize: 18,
                                  color: Colors.black87,
                                  fontWeight: FontWeight.w500,
                                ),
                              ),
                            ),
                          ],
                        );
                      }),
                      Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 8.0),
                        child: Divider(
                          thickness: 0.5,
                        ),
                      ),
                      //SizedBox(height: 16,),
                      Material(
                        //elevation: 1,
                        child: Container(
                          color: Colors.white,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 4.0),
                                child: Text(
                                  'Synopsis',
                                  style: TextStyle(
                                    fontSize: 20,
                                    fontWeight: FontWeight.w600,
                                    color: Color.fromRGBO(29, 52, 97, 1),
                                  ),
                                ),
                              ),
                              SizedBox(
                                height: 8,
                              ),
                              Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 16),
                                child: Text(
                                  (movie.overview != null) ? movie.overview : "Movie overview",
                                  style: TextStyle(
                                    fontSize: 18,
                                    fontWeight: FontWeight.w600,
                                    color: Color.fromRGBO(29, 52, 97, 0.7),
                                  ),
                                ),
                              ),
                              SizedBox(
                                height: 8,
                              ),
                            ],
                          ),
                        ),
                      ),
                      SizedBox(height: 16),
                      Container(
                        color: Colors.black87,
                        height: 200,
                        width: double.infinity,
                        child: ListView.builder(
                          scrollDirection: Axis.horizontal,
                          shrinkWrap: true,
                          itemCount: (movie.actors != null) ? movie.actors.length : 0,
                          itemBuilder: (BuildContext context, int index) {
                            return Container(
                              width: 130,
                              child: Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 8.0),
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: <Widget>[
                                    SizedBox(
                                      height: 16,
                                    ),
                                    Image(
                                      height: 100,
                                      image: NetworkImage(movie.actors[index].profile_path),
                                    ),
                                    SizedBox(
                                      height: 8,
                                    ),
                                    Text(
                                      movie.actors[index].name,
                                      textAlign: TextAlign.center,
                                      style: TextStyle(
                                        fontWeight: FontWeight.w600,
                                        color: Colors.white,
                                      ),
                                    ),
                                    SizedBox(
                                      height: 8,
                                    ),
                                    Flexible(
                                      child: Text(
                                        movie.actors[index].character,
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          color: Colors.white,
                                        ),
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            );
                          },
                        ),
                      ),
                    ],
                  )
                : Padding(
                    padding: const EdgeInsets.only(top: 100),
                    child: Text(
                      'Loading...',
                      textAlign: TextAlign.center,
                    ),
                  ),
          ),
        );
      }),
    );
  }
}
