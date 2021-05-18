import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/screens/following_users_page.dart';
import 'package:movie_app/presentation/screens/profile_page.dart';
import 'package:movie_app/routes/routes.dart';
import 'package:http/http.dart';

class AppDrawer extends StatelessWidget {
  AppApi _api = AppApi(client: Client());
  List<AppUser> users = [];

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        children: <Widget>[
          UserAccountsDrawerHeader(
            decoration: BoxDecoration(
              color: Color.fromRGBO(238, 108, 77, 1),
            ),
            accountName: Text(
              StoreProvider.of<AppState>(context).state.authState.user.displayName,
            ),
            accountEmail: Text(
              StoreProvider.of<AppState>(context).state.authState.user.email,
            ),
          ),
          ListTile(
            leading: Icon(
              Icons.account_circle_rounded,
              color: Colors.black87,
            ),
            title: Text('Profile'),
            onTap: () async => {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => ProfilePage(
                    user: StoreProvider.of<AppState>(context).state.authState.user,
                    loggedUser: StoreProvider.of<AppState>(context).state.authState.user,
                  ),
                ),
              ),
            },
          ),
          ListTile(
            leading: Icon(
              Icons.home,
              color: Colors.black87,
            ),
            title: Text('Home'),
            onTap: () async => {
              Navigator.pushNamed(
                context,
                AppRoutes.home,
              ),
            },
          ),
          ListTile(
            leading: Icon(
              Icons.search,
              color: Colors.black87,
            ),
            title: Text('Search users'),
            onTap: () => {
              Navigator.pushNamed(context, AppRoutes.search_user)
            },
          ),
          ListTile(
            leading: Icon(
              Icons.people_alt_outlined,
              color: Colors.black87,
            ),
            title: Text('Following'),
            onTap: () async => {
              users = await _api.getFollowing(user: StoreProvider.of<AppState>(context).state.authState.user),
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => FollowingUsersPage(users: users),
                ),
              ),
            },
          ),
        ],
      ),
    );
  }
}
