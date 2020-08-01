import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './conversation.reducer';
import { IConversation } from 'app/shared/model/conversation.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IConversationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Conversation = (props: IConversationProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { conversationList, match, loading } = props;
  return (
    <div>
      <h2 id="conversation-heading">
        <Translate contentKey="adjustApp.conversation.home.title">Conversations</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.conversation.home.createLabel">Create new Conversation</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {conversationList && conversationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.conversation.client">Client</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.conversation.specialist">Specialist</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {conversationList.map((conversation, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${conversation.id}`} color="link" size="sm">
                      {conversation.id}
                    </Button>
                  </td>
                  <td>{conversation.clientId ? <Link to={`adjust-client/${conversation.clientId}`}>{conversation.clientId}</Link> : ''}</td>
                  <td>
                    {conversation.specialistId ? (
                      <Link to={`specialist/${conversation.specialistId}`}>{conversation.specialistId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${conversation.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${conversation.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${conversation.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.conversation.home.notFound">No Conversations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ conversation }: IRootState) => ({
  conversationList: conversation.entities,
  loading: conversation.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Conversation);
