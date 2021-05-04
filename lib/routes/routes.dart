import 'package:flutter/cupertino.dart';
import 'package:movie_app/presentation/screens/home_page.dart';
import 'package:movie_app/presentation/screens/login_page.dart';
import 'package:movie_app/presentation/screens/movie_page.dart';
import 'package:movie_app/presentation/screens/search_users_page.dart';
import 'package:movie_app/presentation/screens/signup_page.dart';

class AppRoutes {
  static const String login = '/login';
  static const String signup = '/signup';
  static const String home = '/home';
  static const String movie = '/movie';
  static const String search_user = '/search_user';


  static final Map<String, WidgetBuilder> routes = <String, WidgetBuilder>{
    login: (BuildContext context) => LoginPage(),
    signup: (BuildContext context) => SignUpPage(),
    home: (BuildContext context) => HomePage(),
    movie: (BuildContext context) => MoviePage(),
    search_user: (BuildContext context) => SearchUsersPage(),
  };


}