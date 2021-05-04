import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/models/app_user.dart';

import 'index.dart';

part 'to_watch.freezed.dart';

@freezed
abstract class ToWatch with _$ToWatch implements AppAction {
  const factory ToWatch(String movieId, AppUser user) = ToWatch$;
  const factory ToWatch.successful(String movieId) = ToWatchSuccessful;

  @Implements(ErrorAction)
  const factory ToWatch.error(dynamic error) = ToWatchError;
}