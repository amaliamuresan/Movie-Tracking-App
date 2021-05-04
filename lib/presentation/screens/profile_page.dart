import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/follow_user.dart';
import 'package:movie_app/containers/user_container.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';
import '../movies_gridview.dart';
import 'package:http/http.dart';

class ProfilePage extends StatefulWidget {
  ProfilePage({Key key, @required this.user, @required this.loggedUser}) : super(key: key);
  final AppUser user;
  final AppUser loggedUser;

  @override
  _ProfilePageState createState() => new _ProfilePageState(AppUser, user);
}

class _ProfilePageState extends State<ProfilePage> with SingleTickerProviderStateMixin {
  TabController _controller;
  AppUser user;
  AppApi _api = AppApi(client: Client());
  List<Movie> watchedMovies = [];
  List<Movie> toWatchMovies = [];

  _ProfilePageState(Type appUser, AppUser user);

  @override
  Future<void> initState() {
    super.initState();
    AppApi _api = AppApi(client: Client());
    _controller = new TabController(length: 2, vsync: this);
    _api.getWatchedMovies(user: widget.loggedUser, userUid: widget.user.uid).then((movies) {
      setState(() {
        watchedMovies = movies;
      });
    });
    _api.getToWatchMovies(user: widget.loggedUser, userUid: widget.user.uid).then((movies) {
      setState(() {
        toWatchMovies = movies;
      });
    });
  }

  Widget build(BuildContext context) {
    AppUser loggedUser = StoreProvider.of<AppState>(context).state.authState.user;

    return Scaffold(
      appBar: AppBar(
        title: (widget.user.uid == widget.loggedUser.uid) ? Text('Profile') : Text(widget.user.displayName),
        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
        actions: <Widget>[
          (widget.user.uid == widget.loggedUser.uid)
              ? IconButton(
                  icon: Icon(
                    Icons.settings,
                    color: Colors.white,
                  ),
                  onPressed: () {},
                )
              : UserContainer(builder: (BuildContext context, AppUser user) {
                  return Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: GestureDetector(
                      onTap: () {
                        StoreProvider.of<AppState>(context).dispatch(FollowUser(widget.user.uid, user));
                      },
                      child: Chip(
                        shape: RoundedRectangleBorder(
                          side: BorderSide(color: Colors.white70, width: 1),
                          borderRadius: BorderRadius.circular(30),
                        ),
                        labelPadding: EdgeInsets.all(2.0),
                        label: Padding(
                          padding: const EdgeInsets.all(4.0),
                          child: UserContainer(
                            builder: (BuildContext context, AppUser user) {
                              return Text(
                                (user.friends.contains(widget.user.uid) ? 'Following' : 'Follow'),
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  color: Colors.white,
                                ),
                              );
                            },
                          ),
                        ),
                        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
                      ),
                    ),
                  );
                }),
        ],
      ),
      bottomNavigationBar: DefaultTabController(
        length: 2,
        child: Container(
          color: Color.fromRGBO(29, 52, 97, 1),
          child: TabBar(controller: _controller, indicatorColor: Color.fromRGBO(238, 108, 77, 1), tabs: [
            GestureDetector(
              onTap: () async {},
              child: Tab(
                text: 'Watched',
                /*icon: Icon(
                  Icons.remove_red_eye_outlined,
                  color: Colors.white,
                ),*/
              ),
            ),
            GestureDetector(
              onTap: () {},
              child: Tab(
                text: 'To watch',
                /*icon: Icon(
                  Icons.add_circle,
                  color: Colors.white,
                ),*/
              ),
            ),
          ]),
        ),
      ),
      body: TabBarView(
        controller: _controller,
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: MoviesGridView(movies: watchedMovies),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: MoviesGridView(movies: toWatchMovies),
          ),
        ],
      ),
    );
  }
}
