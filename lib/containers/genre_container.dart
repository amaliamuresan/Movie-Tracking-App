import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:redux/redux.dart';

class GenreContainer extends StatelessWidget{
  const GenreContainer({Key key, @required this.builder}) : super(key: key);
  final ViewModelBuilder<String> builder;

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, String>(
      builder: builder,
      converter: (Store<AppState> store) {
        return store.state.moviesState.genre;
      },
    );
  }
}