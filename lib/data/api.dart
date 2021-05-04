import 'dart:convert';
import 'package:built_collection/built_collection.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:meta/meta.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/movie.dart';

class AppApi {
  const AppApi({@required http.Client client})
      : assert(client != null),
        _client = client;

  final http.Client _client;
  final String ipAddress = '192.168.0.137';

  Future<AppUser> login({String email, String password}) async {
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'users',
      'login',
    ]);

    final response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password': password,
      }),
    );
    if (response.body != null) {
      print('Print from api.dart ${response.body}');
      if (AppUser.fromJson(jsonDecode(response.body)).uid == null) {
        throw Exception('Error while getting the user ${response.body}');
      } else {
        return AppUser.fromJson(jsonDecode(response.body));
      }
    } else {
      throw Exception('Error while getting the user');
    }
  }

  Future<AppUser> register({@required String email, @required String password, @required String displayName}) async {
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'users',
      'register',
    ]);

    final response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password': password,
        'displayName': displayName,
      }),
    );
    if (response.body != null) {
      if (AppUser.fromJson(jsonDecode(response.body)).uid == null) {
        //print('Print from api.dart ${response.body}');
        throw Exception('Error while getting the user ${response.body}');
      } else {
        return AppUser.fromJson(jsonDecode(response.body));
      }
    } else {
      throw Exception('Error while getting the user');
    }
  }

  Future<BuiltList<Movie>> getMovies(String genre, int page) async {
    print('Am ajuns in api getMovies()');
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'tmdb',
      'discover_movie'
    ], queryParameters: <String, String>{
      'type': 'popularity',
      'page': '$page',
      if (genre != 'All' && genre != null) 'genre': genre,
    });

    final Response response = await _client.get(url);
    final String body = response.body;
    final List<dynamic> list = jsonDecode(body);

    List<Movie> aux = [];
    for (int i = 0; i < list.length; i += 1) {
      aux.add(Movie.fromJson(list[i]));
    }
    BuiltList<Movie> ret = ListBuilder<Movie>(aux).build();
    //print(ret);
    return ret;
  }

  Future<BuiltList<Movie>> getMoviesByName(String movieName) async {
    print('Am ajuns in api getMoviesByName()');
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'tmdb',
      'search_movie'
    ], queryParameters: <String, String>{
      'title': movieName,
    });

    final Response response = await _client.get(url);
    final String body = response.body;
    final List<dynamic> list = jsonDecode(body);

    List<Movie> aux = [];
    for (int i = 0; i < list.length; i += 1) {
      aux.add(Movie.fromJson(list[i]));
    }
    BuiltList<Movie> ret = ListBuilder<Movie>(aux).build();
    if (ret != null) {
      return ret;
    } else {
      return new BuiltList([]);
    }
  }

  Future<Movie> viewMovie(String id) async {
    int i = int.parse(id);
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'tmdb',
      'get_movie'
    ], queryParameters: <String, String>{
      'id': '$i',
    });

    final Response response = await _client.get(url);
    final String body = response.body;
    final dynamic movieJson = jsonDecode(body);
    final Map<String, dynamic> json = jsonDecode(body);
    //json.removeWhere((key, value) => (key == 'imbd_id'));

    Movie movie = Movie.fromJson(movieJson);
    print('Movie from API $movie');

    if (movie != null) {
      return movie;
    } else {
      return new Movie();
    }
  }

  Future<String> addToWatch({@required String movieId, @required AppUser user}) async {
    Uri url;
    Response response;

    if (!user.toWatchMovies.contains(movieId)) {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'add_to_watch_movie',
      ]);
    } else {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'remove_to_watch_movie',
      ]);
    }
    response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'uid': user.uid,
        'movie_id': movieId,
        'token': user.token,
      }),
    );

    print(jsonDecode(response.body));
    return movieId;
  }

  Future<String> addWatched({@required String movieId, @required AppUser user}) async {
    Uri url;
    Response response;

    if (!user.watchedMovies.contains(movieId)) {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'add_watched_movie',
      ]);
    } else {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'remove_watched_movie',
      ]);
    }
    response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'uid': user.uid,
        'movie_id': movieId,
        'token': user.token,
      }),
    );

    print(jsonDecode(response.body));
    return movieId;
  }

    Future<List<Movie>> getToWatchMovies({@required AppUser user, String userUid}) async {
    Uri url;
    Response response;

    url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'users',
      'get_to_watch_movie',
    ]);

    response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'logged_uid': user.uid,
        'user_uid': userUid,
        'token': user.token,
      }),
    );

    final String body = response.body;
    final List<dynamic> list = jsonDecode(body);

    List<Movie> ret = [];
    for (int i = 0; i < list.length; i += 1) {
      ret.add(Movie.fromJson(list[i]));
    }
    print('To watch movie list $ret');
    return ret;
  }

  Future<List<Movie>> getWatchedMovies({@required AppUser user, String userUid}) async {
    Uri url;
    Response response;

    url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'users',
      'get_watched_movie',
    ]);

    response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'logged_uid': user.uid,
        'user_uid': userUid,
        'token': user.token,
      }),
    );

    final String body = response.body;
    final List<dynamic> list = jsonDecode(body);

    List<Movie> ret = [];
    for (int i = 0; i < list.length; i += 1) {
      ret.add(Movie.fromJson(list[i]));
    }
    print('To watch movie list $ret');
    return ret;
  }
}
