import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/containers/movie_container.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';

class MoviePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print('MoviePage');
    print(StoreProvider.of<AppState>(context).state.moviesState.movie);
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
      ),
      body: MovieContainer(builder: (BuildContext context, Movie movie) {
        //while(movie == null)  (context as Element).markNeedsBuild();

        return SingleChildScrollView(
          child: Center(
            child: Column(
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
                          image: NetworkImage(
                            movie.backdrop_path,
                          ),
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
                            movie.title,
                            style: TextStyle(
                              fontSize: 28,
                              fontWeight: FontWeight.w500,
                              color: Colors.white,
                            ),
                          ),
                          SizedBox(
                            height: 6,
                          ),
                          Row(
                            children: [
                              Text(
                                '1h 32m',
                                style: TextStyle(
                                  fontSize: 16,
                                  color: Colors.white70,
                                ),
                              ),
                              SizedBox(
                                width: 18,
                                child: Center(
                                  child: Text(
                                    '•',
                                    style: TextStyle(
                                      fontSize: 16,
                                      color: Colors.white70,
                                    ),
                                  ),
                                ),
                              ),
                              Text(
                                'Drama, Action',
                                style: TextStyle(
                                  fontSize: 16,
                                  color: Colors.white70,
                                ),
                              )
                            ],
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    SizedBox(
                      width: 8,
                    ),
                    Chip(
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
                          'Watched',
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                      backgroundColor: Color.fromRGBO(238, 108, 77, 1),
                    ),
                    SizedBox(width: 6),
                    Chip(
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
                          'To watch',
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                      backgroundColor: Color.fromRGBO(29, 52, 97, 1),
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
                ),
                /*Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: Divider(
                    thickness: 0.8,
                    color: Colors.black54,
                  ),
                ),*/
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
                          padding: const EdgeInsets.symmetric(
                              horizontal: 16.0, vertical: 4.0),
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
                            movie.overview,
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
                  height: 170,
                  width: double.infinity,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    shrinkWrap: true,
                    itemCount: movie.actors.length,
                    itemBuilder: (BuildContext context, int index) {
                      return Container(
                        width: 130,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 8.0),
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              SizedBox(height: 16,),
                              Image(
                                height: 90,
                                image: NetworkImage(
                                    movie.actors[index].profile_path),
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
            ),
          ),
        );
      }),
    );
  }
}
