import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';
import 'package:movie_app/models/app_user.dart';

class AppApi {
  const AppApi({@required http.Client client})
      : assert(client != null),
        _client = client;

  final http.Client _client;

  Future<AppUser> login({String email, String password}) async {
    final Uri url = Uri(scheme: 'http', host: '192.168.1.2', port: 8080, pathSegments: <String>[
      'api',
      'users',
      'login',
    ]);

    final response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password':password,
      }),
    );
    if (response.statusCode == 201) {
      print('Print from api.dart ${response.body}');
      return AppUser.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Error while getting the user');
    }

    /*return AppUser.fromJson({
      'uid' : '32uhr7fg38f',
      'email' : 'user1@company.com',
      'password' : '123456',
    });*/
  }

  Future<AppUser> register({@required String email, @required String password}) async {
    final Uri url = Uri(scheme: 'https', host: '8080', pathSegments: <String>[
      'api',
      'users',
      'register',
    ]);

    final response = await http.post(
      url,
      headers: <String, String>{
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password':password,
      }),
    );
    if (response.statusCode == 201) {
      print('Print from api.dart ${response.body}');
      return AppUser.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Error while getting the user');
    }
  }
}