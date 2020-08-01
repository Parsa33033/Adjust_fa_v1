import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './chat-message.reducer';
import { IChatMessage } from 'app/shared/model/chat-message.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChatMessageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ChatMessage = (props: IChatMessageProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { chatMessageList, match, loading } = props;
  return (
    <div>
      <h2 id="chat-message-heading">
        <Translate contentKey="adjustApp.chatMessage.home.title">Chat Messages</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.chatMessage.home.createLabel">Create new Chat Message</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {chatMessageList && chatMessageList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.clientId">Client Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.clientUsername">Client Username</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.specialistId">Specialist Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.specialistUsername">Specialist Username</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.text">Text</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.voice">Voice</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.chatMessage.conversation">Conversation</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {chatMessageList.map((chatMessage, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${chatMessage.id}`} color="link" size="sm">
                      {chatMessage.id}
                    </Button>
                  </td>
                  <td>{chatMessage.clientId}</td>
                  <td>{chatMessage.clientUsername}</td>
                  <td>{chatMessage.specialistId}</td>
                  <td>{chatMessage.specialistUsername}</td>
                  <td>{chatMessage.text}</td>
                  <td>
                    {chatMessage.voice ? (
                      <div>
                        {chatMessage.voiceContentType ? (
                          <a onClick={openFile(chatMessage.voiceContentType, chatMessage.voice)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {chatMessage.voiceContentType}, {byteSize(chatMessage.voice)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {chatMessage.conversationId ? (
                      <Link to={`conversation/${chatMessage.conversationId}`}>{chatMessage.conversationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${chatMessage.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chatMessage.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chatMessage.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.chatMessage.home.notFound">No Chat Messages found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ chatMessage }: IRootState) => ({
  chatMessageList: chatMessage.entities,
  loading: chatMessage.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChatMessage);
