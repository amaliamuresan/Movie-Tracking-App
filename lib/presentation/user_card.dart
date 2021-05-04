import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/screens/profile_page.dart';

class UserCard extends StatelessWidget {
  UserCard({Key key, @required this.user}) : super(key: key);

  final AppUser user;

  @override
  Widget build(BuildContext context) {
    return Card(
      child: GestureDetector(
        onTap: (){
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => ProfilePage(
                user: user,
                loggedUser: StoreProvider.of<AppState>(context).state.authState.user,
              ),
            ),
          );
        },
        child: Row(
          children: <Widget>[
            SizedBox(width: 4),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 2.0),
              child: Text(
                  user.displayName,
                  style: TextStyle(
                    fontWeight: FontWeight.w700,
                    fontSize: 16,
                    color: Color.fromRGBO(29, 52, 97, 1),
                  ),
              ),
            ),
            SizedBox(width: 4,),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8.0),
              child: Text(
                '(${user.email})',
                  overflow: TextOverflow.ellipsis,
              style: TextStyle(
                fontSize: 15,
                color: Colors.black87
              ),),
            ),
            /*Spacer(),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Chip(
                shape: RoundedRectangleBorder(
                  side: BorderSide(
                      color: Color.fromRGBO(238, 108, 77, 1),
                      width: 1),
                  borderRadius: BorderRadius.circular(30),
                ),
                labelPadding: EdgeInsets.all(2.0),
                avatar: CircleAvatar(
                  child: Icon(
                    Icons.arrow_forward,
                    color: Color.fromRGBO(238, 108, 77, 1),
                  ),
                  backgroundColor: Colors.white70,
                ),
                label: Text(
                  'View profile',
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: Color.fromRGBO(238, 108, 77, 1),
                  ),
                ),
                backgroundColor: Colors.white,
              ),
            ),*/
          ],
        ),
      ),
    );
  }
}
