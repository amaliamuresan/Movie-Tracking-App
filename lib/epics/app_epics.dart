import 'package:meta/meta.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/epics/auth_epics.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:redux_epics/redux_epics.dart';

class AppEpics {
  const AppEpics({@required AppApi api})
      : assert(api != null),
        _api = api;

  final AppApi _api;


  Epic<AppState> get epics {
    return combineEpics(<Epic<AppState>>[
      AuthEpics(api: _api).epics,
    ]);
  }
}