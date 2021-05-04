import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/models/app_user.dart';

import 'index.dart';

part 'add_watched.freezed.dart';

@freezed
abstract class AddWatched with _$AddWatched implements AppAction {
  const factory AddWatched(String movieId, AppUser user) = AddWatched$;
  const factory AddWatched.successful(String movieId) = AddWatchedSuccessful;

  @Implements(ErrorAction)
  const factory AddWatched.error(dynamic error) = AddWatchedError;
}