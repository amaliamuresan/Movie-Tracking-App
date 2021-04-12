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

  Future<AppUser> login({String email, String password}) async {
    final Uri url = Uri(scheme: 'https', host: '192.168.1.2', port: 8080, pathSegments: <String>[
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
        'password':password,
      }),
    );
    if (response.body != null) {
        print('Print from api.dart ${response.body}');
      if(AppUser.fromJson(jsonDecode(response.body)).uid == null) {
        throw Exception('Error while getting the user ${response.body}');
      }
      else {
        return AppUser.fromJson(jsonDecode(response.body));
      }
    } else {
      throw Exception('Error while getting the user');
    }
  }

  Future<AppUser> register({@required String email, @required String password, @required String displayName}) async {
    final Uri url = Uri(scheme: 'https', host: '192.168.1.2', port: 8080, pathSegments: <String>[
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
        'password':password,
        'displayName' : displayName,
      }),
    );
    if (response.body != null) {
      if(AppUser.fromJson(jsonDecode(response.body)).uid == null) {
        //print('Print from api.dart ${response.body}');
        throw Exception('Error while getting the user ${response.body}');
      }
      else {
      return AppUser.fromJson(jsonDecode(response.body));
      }
    } else {
      throw Exception('Error while getting the user');
    }
  }

  Future<BuiltList<Movie>> getMovies() async {
    print('Am ajuns in api getMovies()');
    final Uri url = Uri(scheme: 'https',
        host: '192.168.1.2',
        port: 8080,
        pathSegments: <String>[
          'movies',
        ],
        queryParameters: <String, String>{
          'type' : 'movie',
          //'page' : '1',
          });

    final Response response = await _client.get(url);
    final String body = response.body;
    print('movies: ${response.body}');
    final String list = jsonDecode(body)['Title'];
    print(list);
    //list.map((dynamic json) => Movie.fromJson(json));
    return new BuiltList<Movie>();
  }
}