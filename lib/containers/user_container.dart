import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:redux/redux.dart';

class UserContainer extends StatelessWidget{
  const UserContainer({Key key, @required this.builder}) : super(key: key);
  final ViewModelBuilder<AppUser> builder;

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppUser>(
      builder: builder,
      converter: (Store<AppState> store) {
        return store.state.authState.user;
      },
    );
  }
}