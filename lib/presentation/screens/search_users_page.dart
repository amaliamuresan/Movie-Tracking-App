import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/user_card.dart';
import 'package:http/http.dart';

// ignore: must_be_immutable
class SearchUsersPage extends StatelessWidget {
  List<AppUser> users;
  String query = '';
  final AppApi _api = AppApi(client: Client());

  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: AppBar(
          title: TextFormField(
            validator: (val) => val.isEmpty ? 'Enter a valid name' : null,
            style: TextStyle(
              color: Colors.white70,
            ),
            onChanged: (val) {
              query = val;
            },
            decoration: InputDecoration(
              suffixIcon: GestureDetector(
                onTap: () async {
                  if (query != '') {
                      users = await _api.searchUsers(query, StoreProvider.of<AppState>(context).state.authState.user);
                      (context as Element).markNeedsBuild();
                  }
                },
                child: Icon(
                  Icons.search,
                  color: Colors.white,
                ),
              ),
              hintText: 'Search a user..',
              hintStyle: TextStyle(
                color: Colors.white54,
              ),
              border: InputBorder.none,
            ),
          ),
          backgroundColor: Color.fromRGBO(29, 52, 97, 1),
        ),
        body: Column(
          children: <Widget>[
            Expanded(
              child: ListView.builder(
                shrinkWrap: false,
                itemCount: (users == null) ? 0 : users.length,
                padding: EdgeInsets.all(8),
                itemBuilder: (BuildContext context, int index) {
                  final dynamic user = users[index];
                  return UserCard(user: user);
                },
              ),
            ),
          ],
        ));
  }
}
