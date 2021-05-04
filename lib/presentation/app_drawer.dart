import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/screens/profile_page.dart';
import 'package:movie_app/routes/routes.dart';

class AppDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.only(top: 80),
        children: <Widget>[
          ListTile(
            leading: Icon(Icons.account_circle_rounded,
            color: Color.fromRGBO(238, 108, 77, 1),),
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
            leading: Icon(Icons.home,
              color: Color.fromRGBO(238, 108, 77, 1),
            ),
            title: Text('Home'),
            onTap: () async => {
               Navigator.pushNamed(context, AppRoutes.home,),
            } ,
          ),
          ListTile(
            leading: Icon(Icons.search,
              color: Color.fromRGBO(238, 108, 77, 1),
            ),
            title: Text('Discover'),
            onTap: () async => {} ,
          ),
        ],
      ),
    );
  }
}
