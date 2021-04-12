import 'package:built_collection/built_collection.dart';
import 'package:meta/meta.dart';
import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/actions/index.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:redux_epics/redux_epics.dart';
import 'package:rxdart/rxdart.dart';

class MoviesEpics {
  const MoviesEpics({@required AppApi api})
      : assert(api != null),
        _api = api;

  final AppApi _api;

  Epic<AppState> get epics {
    return combineEpics(<Epic<AppState>>[
      TypedEpic<AppState, GetMovies$>(_getMovies),
    ]);
  }

  Stream<AppAction> _getMovies(Stream<GetMovies$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((GetMovies$ action) =>
        Stream<GetMovies$>.value(action)
            .asyncMap((GetMovies$ action) => _api.getMovies())
            .map((BuiltList<Movie> movies) => GetMovies.successful(movies))
            .onErrorReturnWith((dynamic error) => GetMovies.error(error)));
  }
}