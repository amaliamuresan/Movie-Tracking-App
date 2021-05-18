import 'dart:convert';
import 'package:built_collection/built_collection.dart';
import 'package:crypto/crypto.dart';
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
  final String ipAddress = '192.168.1.3';

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
        'password': generateMd5(password),
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
        'password': generateMd5(password),
        'display_name': displayName,
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

  Future<List<AppUser>> getFollowing({@required AppUser user}) async {
    Uri url;
    Response response;

    List<AppUser> ret = [];
    for (int i = 0; i < user.friends.length; i += 1) {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        user.friends[i],
      ]);

      response = await http.get(
        url,
        headers: <String, String>{
          'Content-Type': 'application/json',
        },);
      print('\n\n\n');
      print(response.body);

      ret.add(AppUser.fromJson(jsonDecode(response.body)));
    }

    print('List useri ret $ret');
    return ret;
  }

  Future<List<AppUser>> searchUsers(String query, AppUser user) async {
    final Uri url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
      'api',
      'users',
      'search_users'
    ]);

    Response response = await http.post(
        url,
        headers: <String, String>{
          'Content-Type': 'application/json',
        },
        body: jsonEncode(<String, String>{
          'uid': user.uid,
          'query': query,
          'token': user.token,
        }), );

    final String body = response.body;
    final List<dynamic> list = jsonDecode(body);

    List<AppUser> ret = [];
    for (int i = 0; i < list.length; i += 1) {
      ret.add(AppUser.fromJson(list[i]));
    }
    return ret;
  }

  Future<String> followUser({String uid, AppUser user}) async {
    Uri url;
    Response response;

    if (!user.friends.contains(uid)) {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'follow_user',
      ]);
    } else {
      url = Uri(scheme: 'https', host: ipAddress, port: 8080, pathSegments: <String>[
        'api',
        'users',
        'unfollow_user',
      ]);
    }
    response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'logged_uid': user.uid,
        'uid': uid,
        'token': user.token,
      }),
    );
    return uid;
  }

  String generateMd5(String input) {
    return md5.convert(utf8.encode(input)).toString();
  }
}
