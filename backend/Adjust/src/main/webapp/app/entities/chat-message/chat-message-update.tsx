import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IConversation } from 'app/shared/model/conversation.model';
import { getEntities as getConversations } from 'app/entities/conversation/conversation.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './chat-message.reducer';
import { IChatMessage } from 'app/shared/model/chat-message.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChatMessageUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChatMessageUpdate = (props: IChatMessageUpdateProps) => {
  const [conversationId, setConversationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { chatMessageEntity, conversations, loading, updating } = props;

  const { voice, voiceContentType } = chatMessageEntity;

  const handleClose = () => {
    props.history.push('/chat-message');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getConversations();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...chatMessageEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.chatMessage.home.createOrEditLabel">
            <Translate contentKey="adjustApp.chatMessage.home.createOrEditLabel">Create or edit a ChatMessage</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : chatMessageEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="chat-message-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="chat-message-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="clientIdLabel" for="chat-message-clientId">
                  <Translate contentKey="adjustApp.chatMessage.clientId">Client Id</Translate>
                </Label>
                <AvField id="chat-message-clientId" type="string" className="form-control" name="clientId" />
              </AvGroup>
              <AvGroup>
                <Label id="clientUsernameLabel" for="chat-message-clientUsername">
                  <Translate contentKey="adjustApp.chatMessage.clientUsername">Client Username</Translate>
                </Label>
                <AvField id="chat-message-clientUsername" type="text" name="clientUsername" />
              </AvGroup>
              <AvGroup>
                <Label id="specialistIdLabel" for="chat-message-specialistId">
                  <Translate contentKey="adjustApp.chatMessage.specialistId">Specialist Id</Translate>
                </Label>
                <AvField id="chat-message-specialistId" type="string" className="form-control" name="specialistId" />
              </AvGroup>
              <AvGroup>
                <Label id="specialistUsernameLabel" for="chat-message-specialistUsername">
                  <Translate contentKey="adjustApp.chatMessage.specialistUsername">Specialist Username</Translate>
                </Label>
                <AvField id="chat-message-specialistUsername" type="text" name="specialistUsername" />
              </AvGroup>
              <AvGroup>
                <Label id="textLabel" for="chat-message-text">
                  <Translate contentKey="adjustApp.chatMessage.text">Text</Translate>
                </Label>
                <AvField id="chat-message-text" type="text" name="text" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="voiceLabel" for="voice">
                    <Translate contentKey="adjustApp.chatMessage.voice">Voice</Translate>
                  </Label>
                  <br />
                  {voice ? (
                    <div>
                      {voiceContentType ? (
                        <a onClick={openFile(voiceContentType, voice)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {voiceContentType}, {byteSize(voice)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('voice')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_voice" type="file" onChange={onBlobChange(false, 'voice')} />
                  <AvInput type="hidden" name="voice" value={voice} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label for="chat-message-conversation">
                  <Translate contentKey="adjustApp.chatMessage.conversation">Conversation</Translate>
                </Label>
                <AvInput id="chat-message-conversation" type="select" className="form-control" name="conversationId">
                  <option value="" key="0" />
                  {conversations
                    ? conversations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/chat-message" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  conversations: storeState.conversation.entities,
  chatMessageEntity: storeState.chatMessage.entity,
  loading: storeState.chatMessage.loading,
  updating: storeState.chatMessage.updating,
  updateSuccess: storeState.chatMessage.updateSuccess,
});

const mapDispatchToProps = {
  getConversations,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChatMessageUpdate);
