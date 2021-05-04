import 'package:flutter/material.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/presentation/user_card.dart';

class FollowingUsersPage extends StatelessWidget {
  FollowingUsersPage({Key key, @required this.users}) : super(key: key);
  final List<AppUser> users;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Users you follow'),
        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
      ),
      body: Column(
        children: <Widget>[
          Expanded(
            child: ListView.builder(
              shrinkWrap: false,
              itemCount: users.length,
              padding: EdgeInsets.all(8),
              itemBuilder: (BuildContext context, int index) {
                final dynamic user = users[index];
                return UserCard(user: user);
              },
            ),
          ),
        ],
      ),
    );
  }
}
