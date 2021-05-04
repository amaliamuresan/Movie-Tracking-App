import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:movie_app/models/app_user.dart';

import 'index.dart';

part 'follow_user.freezed.dart';

@freezed
abstract class FollowUser with _$FollowUser implements AppAction {
  const factory FollowUser(String userToFollowId, AppUser user) = FollowUser$;
  const factory FollowUser.successful(String uid) = FollowUserSuccessful;

  @Implements(ErrorAction)
  const factory FollowUser.error(dynamic error) = FollowUserError;
}